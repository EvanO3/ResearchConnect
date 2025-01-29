const { createClient } = require("@supabase/supabase-js");
require("dotenv").config()
const supabaseURL = process.env.supabaseURL;
const supabaseKey = process.env.supabaseKey;

const supabase =createClient(supabaseURL,supabaseKey)

module.exports=supabase




