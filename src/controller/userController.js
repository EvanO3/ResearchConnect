const supabase = require("../db/db.js")
const validDomains =['@Yorku.ca']

const signUp = async(req,res)=>{
    const {email, password}= req.body

    const isDomainValid = validDomains.some(domain =>email.endsWith(domain))
    if (!isDomainValid) {
      return res
        .status(400)
        .json({
          error: "Only university email addresses can be used to sign up",
        });
    }
    try{
        const {data, error} = await supabase.auth.signUp({email, password})


        if(error) throw error
        return res.status(200).json({message:'User has been signed-In', data})
    }catch(err){
        
        return res.status(500).json({error:err.message})
    }
}

module.exports={signUp}