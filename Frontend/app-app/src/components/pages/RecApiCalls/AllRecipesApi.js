import axios from "axios";
import React, {useState, useEffect, useCallback}from "react";
import {useDropzone} from 'react-dropzone';
import './UserProfiles.css';


export const AllRecipes = ()=> {

    const [AllRecipes, setAllRecipes] = useState([]);

    const fetchAllRecipes = ()=>{
        axios.get("http://localhost:9090/api/v1/users").then(res =>{ //http://3.14.3.79:9090/api/v1/users
            console.log(res);
            setAllRecipes(res.data);
        });
    }

    useEffect(()=>{
        fetchAllRecipes();
    }, [] );

    return AllRecipes.map((user, index) =>{
        return (
            <div key={index}>
                {user.userID ? (
                    <img 
                        src={`http://localhost:9090/api/v1/users/${user.userID}/image/download`} //http://3.14.3.79:9090/api/v1/users
                    />
                ):null}
               <br/>
               <br/>
                <h1>{user.firstname} {user.lastname}</h1>
                <p>User ID: {user.userID}</p>
                <p>Email: {user.email}</p>
                <p>Favorite Recipe: {user.fav_rec}</p>
                <Dropzone {...user}/>
                <br/>
            </div>
        )
    }); 

    function Dropzone({userID}) {
        const onDrop = useCallback(acceptedFiles => {
          const file = acceptedFiles[0];

          console.log(userID);
          console.log(file);

          const formData = new FormData();
          formData.append("file", file);

          axios.post(`http://localhost:9090/api/v1/users/${userID}/image/upload`, //http://3.14.3.79:9090/api/v1/users
                formData, 
                    {
                        headers: {
                         "Content-Type": "image/*"
                        }
                    }  
                ).then(()=>{
                         console.log("file uploaded successfully")
                     }
                ).catch(err =>{
                         console.log(err);
                          })


        }, [])
        const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})
      
        return (
          <div {...getRootProps()}>
            <input {...getInputProps()} />
            {
              isDragActive ?
                <p>Drop the image here ...</p> :
                <p>Drag 'n' drop a profile picture here, or click to select a profile picture</p>
            }
          </div>
        )
    }
}