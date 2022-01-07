import axios from "axios";
import React, {useState, useEffect, useCallback}from "react"
import '../../UserProfiles.css'

export const CurryIng = ()=> {

   

    const [curryIng, setCurryIng] = useState([]);

    const fetchCurryIng = ()=>{
        var recipe = "curry";
        axios.get(`http://localhost:9090/api/v1/recipes/${recipe}`).then(res =>{
            console.log(res);
            setCurryIng(res.data);
        });
        

    }

    useEffect(()=>{
        fetchCurryIng();
    }, [] );

    return curryIng.map((ingredient, index) =>{
        return (
            <div key={index}>
                <p>User ID: {ingredient.ingName}</p>
               
            </div>
        )
    }); 
}