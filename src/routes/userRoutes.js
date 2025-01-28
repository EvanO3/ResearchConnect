const Router = require("express");
const userController = require("../controller/userController")
const router = Router()



//sign up route

router.post("/register", userController.register)



module.exports =router