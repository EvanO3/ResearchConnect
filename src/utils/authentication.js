//auth middleware to verify the jwt token that comes from supabase
const jwt = require('jsonwebtoken')
require('dotenv').config()
const supabase = require("../db/connection.js")
const secret = process.env.jwt_secret;

const handleUserAuth = async(req,res, next)=>{

    try{
        const token = req.headers.authorization?.split(" ")[1]

        if(!token){
            return res.status(401).json({msg:"Unauthorized: No token found"})
        }

        // decoding the JWT

        const {data, error}= await supabase.auth.getUser(token)

        if(error || !data?.user){
            console.log(error.message)
            return res.status(400).json({msg:"Unathorized: Invalid token recieved"})
        }



        //attaching the users info to the request
        req.user=data.user; 


        next();

    }catch(error){
        console.log("Error: ", error)
        return res.status(500).json({msg:"Internal Server Error"})
    }

}


module.exports = handleUserAuth;

