const Router = require("express");
const userController = require("../controller/userController")
const router = Router()
const handleUserAuth = require("../utils/authentication");


//sign up route

router.post("/register", userController.register);
router.post("/login", userController.login);
router.post("/updateProfile", userController.initialUpdateProfile);
router.post("/logout", userController.logout);
//This route gives the ability to edit profiles specific things in profile
 router.patch("/editprofile", userController.updateProfile)
 router.get("profile/:id")

 router.get("/home", handleUserAuth, (req, res) => {
  return res.status(200).json({msg:"Hello World"}) 
 });


 





module.exports =router