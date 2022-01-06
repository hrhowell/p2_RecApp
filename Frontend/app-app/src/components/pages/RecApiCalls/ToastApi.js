import axios from "axios";
import React, {useState, useEffect, useCallback}from "react"
import '../../UserProfiles.css';

export const ToastIng = ()=> {

   

    const [toastIng, setToastIng] = useState([]);

    const fetchToastIng = ()=>{
        axios.get("http://localhost:9090/api/v1/recipes/meals/breakfast/avocado-toast").then(res =>{
            console.log(res);
            setToastIng(res.data);
        });
        

    }

    useEffect(()=>{
        fetchToastIng();
    }, [] );

    return toastIng.map((ingredient, index) =>{
        return (
            <div key={index}>
                <p>User ID: {ingredient.ingName}</p>
               
            </div>
        )
    }); 
}