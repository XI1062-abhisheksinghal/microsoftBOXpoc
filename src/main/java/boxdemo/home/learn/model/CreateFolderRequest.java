package boxdemo.home.learn.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CreateFolderRequest {
	
	String folderName;
	String parentName;

}
