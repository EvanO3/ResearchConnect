const Router = require("express");
const userRoutes = require("./userRoutes")
const router = Router();

router.use("/api", userRoutes);

module.exports = router;
