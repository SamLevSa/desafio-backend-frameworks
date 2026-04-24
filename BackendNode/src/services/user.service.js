const userRepository = require('../repositories/user.repository');

const findAll = async () => userRepository.findAll();

const findById = async (id) => {
  const user = await userRepository.findById(id);
  if (!user) throw new Error('User not found');
  return user;
};

const create = async ({ name, email }) => {
  if (!name || !email) throw new Error('Name and email are required');
  return userRepository.create({ name, email });
};

const remove = async (id) => {
  await findById(id);
  return userRepository.remove(id);
};

module.exports = { findAll, findById, create, remove };