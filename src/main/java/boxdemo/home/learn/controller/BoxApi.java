package boxdemo.home.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;

import boxdemo.home.learn.model.BoxFolderResponse;
import boxdemo.home.learn.model.CreateFolderRequest;
import boxdemo.home.learn.service.FolderService;


@RestController
@RequestMapping("/box")
public class BoxApi {
	
	@Autowired 
	private FolderService folderService;
	
	
	//First login to the Box Account , create a dummy app and then generate the token .
	private String token = "mVMw8v2jU5B1ttGT0VdeH4ve9W9pUFCx";
	
	@GetMapping
	@RequestMapping("/getrootdetails")
	public void boxConnection() {
	
	BoxAPIConnection api = new BoxAPIConnection(token);
	BoxFolder rootFolder = BoxFolder.getRootFolder(api);
	System.out.println("======"+rootFolder.getInfo().getParent());
	
	//Create a new folder with the folder ID 
	//rootFolder.createFolder("Demo folder");
	
	BoxFolder.Info info1 = rootFolder.getInfo("size", "owned_by");
	
     System.out.println("Folder info===="+info1.getName());
     
     //Create a new folder in BOX 
     BoxFolder parentFolder = new BoxFolder(api, "First Folder");
     BoxFolder.Info childFolderInfo = parentFolder.createFolder("folderforloc");
     
     System.out.println("Name of folder which is created =========="+childFolderInfo.getName());
     
	//rootFolder.collaborate("Abhishek_Singhal-GGN@external.mckinsey.com",Role.EDITOR);
	
	//if(!rootFolder.getCollaborations().isEmpty()) {
	//Collection<BoxCollaboration.Info> collaborations= rootFolder.getCollaborations();
	//}
 	//System.out.println("====Box collaborators"+  collaborations.toString());
	
	for (BoxItem.Info itemInfo : rootFolder) {
		System.out.println("prints all the default folder that the user gets once the App is created on Microsoft Box");
	    System.out.format("===[%s] %s\n======", itemInfo.getID(), itemInfo.getName());
	    
	    
	    System.out.println("Owner of getOwned By of boxItem"+itemInfo.getOwnedBy());
	   // BoxFolder.Info info = rootFolder.new Info();
	   // info.setName("Abhishek personal Data");
	   // rootFolder.updateInfo(info);
	    
	}
	
	}
	
	@PostMapping
	@RequestMapping("/createFolder")
	public ResponseEntity<BoxFolderResponse> createFolder(@RequestBody CreateFolderRequest createFolder){
		
		return folderService.createFolder(createFolder.getFolderName(), createFolder.getParentName() );
	}
}
