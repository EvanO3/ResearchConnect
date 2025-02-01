const supabase = require("../db/connection.js");
const validDomains = ["@hotmail.com"];

const register = async (req, res) => {
  console.log("Request body:", req.body);
  const body = { ...req.body };

  const isDomainValid = validDomains.some((domain) =>
    body.email.endsWith(domain)
  );
  if (!body.email || !isDomainValid) {
    return res.status(400).json({
      error: "Only university email addresses can be used to sign up",
    });
  }

  console.log("Email: ", body.email);
  try {
    console.log("Attempting Supabase signUp with email:", body.email);
    let { data, error } = await supabase.auth.signUp({
      email: body.email,
      password: body.password,
      options: {
        shouldCreateUser: true,
      },
    });

    console.log("SignUp Response:", { data, error });

    if (error) {
      console.log("Error loading");
      return res
        .status(400)
        .json({ error: error.message, errorstack: error.stack });
    }

    return res.status(200).json({ message: "User has been signed-In", data });
  } catch (err) {
    console.error(err.stack);

    return res.status(500).json({ error: err.message });
  }
};

module.exports = { register };
