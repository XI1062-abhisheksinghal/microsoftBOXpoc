package boxdemo.home.learn.service;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFolder;

import boxdemo.home.learn.config.AppBoxConfig;
import boxdemo.home.learn.exception.BoxException;
import boxdemo.home.learn.model.BoxFolderResponse;
import boxdemo.home.learn.model.CreateBoxFolder;
import boxdemo.home.learn.model.ParentBoxFolder;

@Service
public class FolderService {
	private static final Logger LOGGER = LoggerFactory.getLogger(FolderService.class);
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired 
	AppBoxConfig appBoxConfig;
	

	public static final String ERROR_WHILE_CREATING_FOLDER_WITH_NAME_PARENT_ID = "Error while creating folder with name = {}, parentId = {}";

	private String createFolderUrl = "https://api.box.com/2.0/folders";

	public ResponseEntity<BoxFolderResponse> createFolder(String name, String parentId) {
        LOGGER.info("making connection to the Box Client");
          appBoxConfig.createBoxConnection();
		LOGGER.debug("createFolder <START> folderId={}, parent folder id={}", name, parentId);
		CreateBoxFolder createBoxFolder = getCreateBoxFolderDTO(name, parentId);
		try {
			LOGGER.info("made connection to the box client");
			
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.set("Authorization", "Bearer O3YnHI0OMV3q1DaCaLMJ78l6MhpH1rOQ");
            
			HttpEntity<CreateBoxFolder> entity = new HttpEntity<>(createBoxFolder, headers);
			ResponseEntity<BoxFolderResponse> boxFolderResponse = restTemplate.postForEntity(createFolderUrl,
					entity, BoxFolderResponse.class);
			
			LOGGER.debug("createFolder <END> folderId={}, parent folder id={}", name, parentId);
			return boxFolderResponse;
		} catch (Exception exception) {
			LOGGER.error(ERROR_WHILE_CREATING_FOLDER_WITH_NAME_PARENT_ID, name, parentId, exception);
			throw new BoxException(exception.getMessage());
		}
	}

	private CreateBoxFolder getCreateBoxFolderDTO(String name, String parentId) {
		return CreateBoxFolder.builder().name(name).parent(ParentBoxFolder.builder().id(parentId).build()).build();
	}

}
