const express = require("express");
const app = express();
require("dotenv").config();
const routes = require("./routes/Routes");
const supabase = require("./db/connection.js");
const fileUpload = require("express-fileupload");
const PORT = process.env.PORT || 3000;
app.use(fileUpload());
app.use(express.json());
app.use(routes);
app.listen(PORT, () => {
  console.log(` Server is running and Listening on port ${PORT}`);
});


(async ()=>{
    try{
        const {data , error} = await supabase.rpc("now");
        if(error){
            console.error("Database connection error:", error.message)
        }else{
            console.log("Database successfully connected")
        }
    }catch(error){
        console.error('Internal server error', error)
    }
})