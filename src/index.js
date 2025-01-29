const express = require("express")
const app = express()
require("dotenv").config()
const routes = require("./routes/Routes")


const PORT =process.env.PORT || 3000
app.use(express.json())
app.use(routes)







app.listen(PORT, ()=>{
    console.log(`Listening on port ${PORT}`)
})






