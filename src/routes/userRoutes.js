const Router = require("express");
const userController = require("../controller/userController")
const router = Router()



//sign up route

router.post("/register", userController.register)
router.post("/login", userController.login)
router.post("/updateProfile", userController.updateProfile);



module.exports =router