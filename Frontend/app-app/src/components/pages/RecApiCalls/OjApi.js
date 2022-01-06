import axios from "axios";
import React, {useState, useEffect, useCallback}from "react"
import '../../UserProfiles.css';

export const OrangeJuiceIng = ()=> {

   

    const [orangeJuiceIng, setOrangeJuiceIng] = useState([]);

    const fetchCurryIng = ()=>{
        axios.get("http://localhost:9090/api/v1/recipes/meals/beverages/orange-juice").then(res =>{
            console.log(res);
            setOrangeJuiceIng(res.data);
        });
        

    }

    useEffect(()=>{
        fetchOrangeJuiceIng();
    }, [] );

    return orangeJuiceIng.map((ingredient, index) =>{
        return (
            <div key={index}>
                <p>User ID: {ingredient.ingName}</p>
               
            </div>
        )
    }); 
}