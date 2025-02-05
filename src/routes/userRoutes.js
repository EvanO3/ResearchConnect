const Router = require("express");
const userController = require("../controller/userController")
const router = Router()



//sign up route

router.post("/register", userController.register);
router.post("/login", userController.login);
router.post("/updateProfile", userController.initialUpdateProfile);
//This route gives the ability to edit profiles specific things in profile
// router.put()



router.post("/logout", userController.logout);



module.exports =router