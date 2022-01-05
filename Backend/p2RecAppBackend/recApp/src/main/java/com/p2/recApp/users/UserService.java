package com.p2.recApp.users;

import java.time.LocalDateTime;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.apache.http.entity.ContentType.*;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.p2.recApp.bucket.BucketName;
import com.p2.recApp.filestore.FileStore;
import com.p2.recApp.registration.token.ConfirmationToken;
import com.p2.recApp.registration.token.ConfirmationTokenService;

import lombok.AllArgsConstructor;
/*************************************Works Cited*********************************************
 * Title: "Java Tutorial - Complete User Login and Registration Backend + Email Verification"
 * Author: Nelson (amigoscode)
 * Date: 1/17/21 (Accessed 12/29/21)
 * Code Version: Java 15
 * Availability: https://youtu.be/QwQuro7ekvc
 * 
 * Title: "Spring Boot Tutorial | Spring Boot Full Stack with React.js | Full Course | 2021"
 * Author: Nelson (amigoscode)
 * Date: 3/28/20 (Accessed 12/29/21)
 * Code Version: Java 15
 * Availability: https://youtu.be/i-hoSg8iRG0
 *********************************************************************************************/

@Service
<<<<<<< HEAD

//may need to implement user details for update
public class UserService/* implements UserDetailsService*/ {
=======
@AllArgsConstructor
public class UserService {
>>>>>>> 8b815e6e41071e50229d9107d4b26aa6c70b3890

	//	private  BCryptPasswordEncoder bCryptPasswordEncoder;
	//	
	//	private final static String USER_NOT_FOUND_MSG = 
	//			"user with email %s not found";
	//	@Override
	//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	//		// TODO Auto-generated method stub
	//		return userRepository.findByEmail(email)
	//				.orElseThrow(
	//						() -> new UsernameNotFoundException(
	//								String.format(USER_NOT_FOUND_MSG, email)
	//								));
	//}

	
	
	private final FileStore fileStore;
	private final UserRepository userRepository;
	private Integer userID;
	private final static String USER_NOT_FOUND_MSG = 
			"user with email %s not found";
	private final ConfirmationTokenService confirmationTokenService;
	
	public String signUpUser(User user) {

		boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();

		if(userExists) {
			throw new IllegalStateException("email taken");
		}

		//	String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

		//	user.setPassword(encodedPassword);

		userRepository.save(user);

		//TODO: send confirmation token 

<<<<<<< HEAD
		return "";
	}
	
	//Do we not need to enable user?
=======
		String token = UUID.randomUUID().toString();
		ConfirmationToken cofirmationToken = new ConfirmationToken(
				token,
				LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(15),
				user
		);
		
		confirmationTokenService.saveConfirmationToken(cofirmationToken);
>>>>>>> 8b815e6e41071e50229d9107d4b26aa6c70b3890

//		TODO: SEND EMAIL
		
		
		return token;
	}
	
	@Autowired
	public UserService(UserRepository userRepository, FileStore fileStore) {
		this.fileStore = fileStore;
		this.userRepository = userRepository;
		this.confirmationTokenService = null;
	}

	List <User> getUserProfiles(){

		return userRepository.findAll();
	}
	
	public void addUser(
			String firstname,
			String lastname,
			String emial,
			String username,
			String password,
			String profile_pic,
			String fav_rec) {
		
	}
//	public void updateUser(){
		//check user info
		//get info
		//set it
		//post
//	}
	
	
	void uploadUserProfileImage(Integer userID, MultipartFile file) {
        // 1. Check if image is not empty
        isFileEmpty(file);
        // 2. If file is an image
        isImage(file);

        // 3. The user exists in our database
        User user = getUserProfileOrThrow(userID);

        // 4. Grab some metadata from file if any
        Map<String, String> metadata = extractMetadata(file);

        // 5. Store the image in s3 and update database (userProfileImageLink) with s3 image link
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getUserID());
        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

        try {
            fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
            user.setProfile_pic(filename);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }
	
	  byte[] downloadUserProfileImage(Integer userID) {
	        User user = getUserProfileOrThrow(userID);

	        String path = String.format("%s/%s",
	                BucketName.PROFILE_IMAGE.getBucketName(),
	                user.getUserID());

	        return user.getProfile_pic()
	                .map(key -> fileStore.download(path, key))
	                .orElse(new byte[0]);

	    }
		
		
		private Map<String, String> extractMetadata(MultipartFile file) {
	        Map<String, String> metadata = new HashMap<>();
	        metadata.put("Content-Type", file.getContentType());
	        metadata.put("Content-Length", String.valueOf(file.getSize()));
	        return metadata;
	    }
		
		//throws an exception about string to integer
		  private User getUserProfileOrThrow(Integer userID) {
		        return userRepository
		                .findById(userID)
		                .stream()
		                .filter(userProfile -> userProfile.getUserID().equals(userID))
		                .findFirst()
		                .orElseThrow(() -> new IllegalStateException(String.format("User profile %s not found", userID)));
		    }
		  private void isImage(MultipartFile file) {
		        if (!Arrays.asList(
		                IMAGE_JPEG.getMimeType(),
		                IMAGE_PNG.getMimeType(),
		                IMAGE_GIF.getMimeType()).contains(file.getContentType())) {
		            throw new IllegalStateException("File must be an image [" + file.getContentType() + "]");
		        }
		    }

		    private void isFileEmpty(MultipartFile file) {
		        if (file.isEmpty()) {
		            throw new IllegalStateException("Cannot upload empty file [ " + file.getSize() + "]");
		        }
		    }

		    public int enableUser(String email) {
		        return userRepository.enableUser(email);
		    }


	

}
