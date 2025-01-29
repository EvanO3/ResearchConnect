const supabase = require("../db/db.js")
const validDomains =['@hotmail.com']

const register = async(req,res)=>{
    console.log("Request body:", req.body);
    const body ={...req.body}
    
    const isDomainValid = validDomains.some(domain =>body.email.endsWith(domain))
    if (!isDomainValid) {
      return res
        .status(400)
        .json({
          error: "Only university email addresses can be used to sign up",
        });
    }

    console.log("Email: ", body.email);
    try{
        console.log("Attempting Supabase signInWithOtp with email:", body.email);
      let { data, error } = await supabase.auth.signInWithOtp({
        email: body.email,
        options:{
            shouldCreateUser:true
        }
      });

      
      console.log("SignUp Response:", { data, error });

      if (error) {
        console.log("Error loading");
        return res
          .status(400)
          .json({ error: error.message, errorstack: error.stack });
      }

          const { data: profileData, error: profileError } = await supabase
          .from("profiles")
          .insert([
              {
                  user_id: data.id,
                  full_name: body.full_name,
                  email: body.email,
                  role: body.role,
              },
          ]);

          if(profileError){

              return res.status(400).json({error:profileError.message})
            }

            if (body.role === "student") {
              const { data: studentData, error: studentError } = await supabase
                .from("Student")
                .insert([
                  {
                    user_id: profileData.id,
                    Major: body.Major,
                    Minor: body.Minor || "",
                    Class:body.Class,
                    Graduation_Year: body.Graduation_Year,
                    gpa: body.GPA

                  },
                ]);
            } else if (body.role === "professor") {
              const {professorData, professorError} = await  supabase.from("Professor").insert([{

                  user_id:profileData.id,
                  Title:body.Title,
                  Department:body.Department

              }])
            }
            else {
              return res.status(400).json({ message: "Invalid role selected" });
            }

      return res.status(200).json({ message: "User has been signed-In", data });
    }catch(err){
        console.error(err.stack)
       
        return res.status(500).json({error:err.message})
    }
}

module.exports={register}