const mongoose = require("mongoose");
const schema = mongoose.Schema;

const userSchema = new schema({
  // name: { type: String, required: true },
  //   email: { type: String, required: true },
  first_name: { type: String, required: true },
  last_name: { type: String, required: true },
  username: { type: String, required: true, unique: true },
  email: { type: String, required: true, unique: true },
  phone_num: { type: String, required: true, unique: true },
  room_no: { type: String, required: true },
  password: { type: String, required: true },
});

const User = (module.exports = mongoose.model("User", userSchema));
