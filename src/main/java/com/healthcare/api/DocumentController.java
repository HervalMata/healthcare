package com.healthcare.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.MultipartConfigElement;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.healthcare.api.model.DocumentRequest;
import com.healthcare.exception.ApplicationException;
import com.healthcare.model.entity.Document;
import com.healthcare.model.enums.DocumentStatusEnum;
import com.healthcare.model.response.Response;
import com.healthcare.service.DocumentService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/api/document")
public class DocumentController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MultipartConfigElement multipartConfigElement;

	@Autowired
	private DocumentService documentService;

	@ApiOperation(value = "upload document", notes = "upload a new document")
	@ApiParam(name = "documentRequest", value = "document to update", required = true)
	@PostMapping
	public ResponseEntity<Document> upload(@RequestParam("file") MultipartFile file, DocumentRequest documentRequest) {
		logger.info("upload document");
		Document document = new Document();
		if ((file != null && file.isEmpty()) || StringUtils.isBlank(documentRequest.getEntity())
				|| (documentRequest.getEntityId() == null || documentRequest.getEntityId() == 0)
				|| StringUtils.isBlank(documentRequest.getFileClass())) {
			return new ResponseEntity<Document>(document, HttpStatus.BAD_REQUEST);
		}
		try {
			// Get attributes
			document.setEntity(documentRequest.getEntity());
			document.setEntityId(documentRequest.getEntityId());
			document.setFileClass(documentRequest.getFileClass());
			document.setStatus(DocumentStatusEnum.ACTIVE.getValue());
			// Get the file
			if (file != null) {
				processFile(file, document);
			}

		} catch (Exception e) {
			logger.error("Error in loading file", e);
			return new ResponseEntity<Document>(document, HttpStatus.BAD_REQUEST);
		}
		document = documentService.save(document);
		return new ResponseEntity<Document>(document, HttpStatus.OK);
	}

	@ApiOperation(value = "get document by Id", notes = "get document detail by id")
	@ApiImplicitParam(name = "id", value = "document Id", required = true, dataType = "Long")
	@GetMapping(value = "/{id}")
	public Document read(@PathVariable Long id) {
		return documentService.findById(id);
	}

	@ApiOperation(value = "update document", notes = "update a document")
	@ApiParam(name = "documentRequest", value = "document to update", required = true)
	@PutMapping
	public ResponseEntity<Document> update(@RequestParam("file") MultipartFile file, DocumentRequest documentRequest) {
		Document document = documentService.findById(documentRequest.getId());

		if (file != null && file.isEmpty()) {
			return new ResponseEntity<Document>(document, HttpStatus.BAD_REQUEST);
		}
		try {
			if (StringUtils.isNotBlank(documentRequest.getEntity())) {
				document.setEntity(documentRequest.getEntity());
			}
			if (documentRequest.getEntityId() != null && documentRequest.getEntityId() > 0) {
				document.setEntityId(documentRequest.getEntityId());
			}
			if (StringUtils.isNotBlank(documentRequest.getFileClass())) {
				document.setFileClass(documentRequest.getFileClass());
			}

			// Store and get values from file
			if (file != null) {
				processFile(file, document);
			}
		} catch (IOException e) {
			logger.error("Error in loading file", e);
			return new ResponseEntity<Document>(document, HttpStatus.BAD_REQUEST);
		}
		document = documentService.save(document);
		return new ResponseEntity<Document>(document, HttpStatus.OK);
	}

	@ApiOperation(value = "delete document", notes = "delete a document")
	@ApiImplicitParam(name = "id", value = "document Id", required = true, dataType = "Long")
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Long id) {
		Document document = documentService.findById(id);
		try {
			Path path = Paths.get(document.getFilePath() + document.getFileName());
			Files.delete(path);
		} catch (IOException e) {
			logger.error("Unable de delete file", e);
			throw new ApplicationException(Response.ResultCode.ERROR,
					"Unable de delete file: " + document.getFileName());
		}
		documentService.deleteById(id);
	}

	/**
	 * Get file information
	 * 
	 * @param file
	 *            : File
	 * @param document
	 *            : Document
	 * @throws IOException
	 *             : Exception
	 */
	private void processFile(MultipartFile file, Document document) throws IOException {
		// TODO : The strategy of storing files can be changed
		byte[] bytes = file.getBytes();
		Path path = Paths.get(multipartConfigElement.getLocation() + file.getOriginalFilename());
		Files.write(path, bytes);

		document.setFilePath(multipartConfigElement.getLocation());
		document.setFileUrl(path.toString());
		document.setFileName(file.getOriginalFilename());
		document.setFileSize(file.getSize());

		// Get type file
		String[] fileFrags = file.getOriginalFilename().split("\\.");
		String extension = fileFrags[fileFrags.length - 1];
		document.setFileType(extension);
	}
}
