import axios from "axios";
import React, {useState, useEffect, useCallback}from "react"
import Navbar from "../../Navbar";
import '../../UserProfiles.css';
import  { PadThaiIng } from '../RecApiCalls/PadThaiApi';

function PadThai(){
    return(
    <>
    <Navbar/>
    <PadThaiIng/>
    </>
    )
        

}
export default PadThai;