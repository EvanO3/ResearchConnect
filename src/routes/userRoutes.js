const Router = require("express");
const userController = require("../controller/userController")
const router = Router()



//sign up route

router.post("/register", userController.register);
router.post("/login", userController.login);
router.post("/updateProfile", userController.initialUpdateProfile);
router.post("/logout", userController.logout);
//This route gives the ability to edit profiles specific things in profile
 router.patch("/editprofile", userController.updateProfile)
 router.get("profile/:id")







module.exports =router