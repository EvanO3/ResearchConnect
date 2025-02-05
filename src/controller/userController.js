const supabase = require("../db/connection.js");
const validDomains = ["@gmail.com"];

//This allows for errors to be handled in a single place rather than repeating the same 400 logic
const handleError = (res,error,statusCode=400) =>{
  console.log('Error: ', error.message)
  return res.status(statusCode).json({error: error.message})
}
/**supbase automatically enforces unqiue emails so duplicate emails will be rejected */
const register = async (req, res) => {
  console.log("Request body:", req.body);
  const {email, password } = req.body;

  
  const isDomainValid = validDomains.some((domain) =>
    email.endsWith(domain)
  );
  if (!email || !isDomainValid) {
    return res.status(400).json({
      error: "Only university email addresses can be used to sign up",
    });
  }

  console.log("Email: ", email);
  try {
    console.log("Attempting Supabase signUp with email:", email);
    let { data, error } = await supabase.auth.signUp({
      email: email,
      password:password,
      options: {
        shouldCreateUser: true,
      },
    });

    console.log("SignUp Response:", { data, error });

    if (error) {
     
      return res
        .status(400)
        .json({ error: error.message, errorstack: error.stack });
    }

    return res.status(201).json({ message: "User has been created", data });
  } catch (err) {
    console.error(err.stack);

    return res.status(500).json({ error: err.message });
  }


};


const login = async (req, res)=>{
  const {email, password}= req.body;

  if(!email||!password){
    return res.status(400).json({error:"Field missing"});
  }

  try{
    console.log('Attempting to sign in with supabase');

    let {data, error}= await supabase.auth.signInWithPassword({
      email:email,
      password:password
    })
    
    if(error){
      return res.status(400).json({error:error})
    }


    return res.status(200).json({user:data})

  }catch(err){
    console.log(`Error occurred: ${err} `)
    return res.status(500).json({error:"Internal server error"})
  }
}

//adds the users information to their respective db depending on the role, 
// 
// 



/*---------------------------------------

insert their data in the users table first

-----------------------------------------*/

//on update, grab exisitng user data there so they only update the specific field
const updateProfile = async (req,res)=>{
  const body = req.body;

  if(!body.role){
    return res.status(400).json({msg:"Please provide role: Student or Professor"})
  }
  
 try{
   const {data, error} = await supabase.auth.getUser();

  if(error || !data?.user?.id){
    res.status(500).json({error:'failed to retrieve user information'})
  }

    const userId = data.user.id;
    const email = data.user.email;
    
    //check to see if the user even exists in the table
    const {data:existingUser, error:userError} = await supabase.from("users").select('*').eq("user_id", userId).maybeSingle()
    console.log(existingUser)
    if(userError){
    return handleError(res, userError) 
    }

    if(!existingUser){

      const { error: insertError } = await supabase
        .from("users")
        .insert({
          user_id:userId,
          first_name: body.firstName,
          last_name: body.lastName,
          role:body.role,
          email:email
        }); 
        if(insertError){
          return handleError(res,insertError)
        }
    }

    //find the user id so i can reference to input the data 

    const {data: userData, error:userDataError} = await supabase
    .from('users')
    .select('id')
    .eq('user_id', userId)
    .single()

    //now given the primary key in the users database we can use the it as a foreign key in student or professor db

    const actualUserId = userData?.id


    

if(body.role ==='student'){
const { error: studentInsertError } = await supabase.from("students").insert({
  major: body.major,
  minor: body.minor,
  graduation_year: body.graduation_year,
  gpa: body.gpa,
  school_name: body.school_name,
  user_id: actualUserId,
}); 

if(studentInsertError){
  return  handleError(res, studentInsertError)
}
return res.status(201).json({msg:"Successfully updated profile as a Student"})

}

if(body.role==='professor'){
const { error: professorInsertError } = await supabase
  .from("professors")
  .insert({
    title: body.title,
    department: body.department,
    educational_institution: body.educational_institution,
    user_id: actualUserId,
  }); 


if (professorInsertError) {
  return handleError(res, professorInsertError)
}
return res.status(201).json({ msg: "Successfully updated profile" });
}

 }catch(err){
  console.log('Error has occured ', err.message)
  return res.status(500).json({error:"Internal Server Error"})
 }

}


// route for if the user exists then update using patch request

module.exports = { register, login, updateProfile };
