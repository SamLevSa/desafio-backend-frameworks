const User = require('../models/user.model');

const findAll = () => User.findAll();
const findById = (id) => User.findByPk(id);
const create = (data) => User.create(data);
const remove = (id) => User.destroy({ where: { id } });

module.exports = { findAll, findById, create, remove };