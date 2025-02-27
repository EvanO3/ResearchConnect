const Router = require("express");
const userController = require("../controller/userController")
const router = Router()
const handleUserAuth = require("../utils/authentication");
const fileUpload = require("express-fileupload")

//sign up route

router.post("/register", userController.register);
router.post("/login", userController.login);
router.post("/updateProfile", userController.initialUpdateProfile);
router.post("/logout", userController.logout);


// this route was used to test uploading a file, use this logic to extract the resume and then upload resume.data to the 
//supabase bucket
router.post("/home", async (req,res)=>{
if (!req.files || !req.files.name) {
  return res.status(400).json({ error: "No file uploaded" });
}

const resume = req.files.name; // Access the nested file
console.log(resume.name); // File name
console.log(resume.mimetype); // MIME type (e.g., application/vnd.openxmlformats-officedocument.wordprocessingml.document)
console.log(resume.data); // Binary data (buffer)

res.json({ message: "File received successfully", fileName: resume.name });
})

//This route gives the ability to edit profiles specific things in profile
 router.patch("/editprofile",handleUserAuth, userController.updateProfile)
router.post("/uploadResume", handleUserAuth, userController.uploadResume)


 





module.exports =router