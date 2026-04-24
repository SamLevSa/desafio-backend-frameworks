const userService = require('../services/user.service');

const findAll = async (req, res, next) => {
  try {
    const users = await userService.findAll();
    res.json(users);
  } catch (err) { next(err); }
};

const findById = async (req, res, next) => {
  try {
    const user = await userService.findById(req.params.id);
    res.json(user);
  } catch (err) { next(err); }
};

const create = async (req, res, next) => {
  try {
    const user = await userService.create(req.body);
    res.status(201).json(user);
  } catch (err) { next(err); }
};

const remove = async (req, res, next) => {
  try {
    await userService.remove(req.params.id);
    res.status(204).send();
  } catch (err) { next(err); }
};

module.exports = { findAll, findById, create, remove };