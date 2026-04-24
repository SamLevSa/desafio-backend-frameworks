const { Router } = require('express');
const controller = require('../controllers/user.controller');

const router = Router();

router.get('/', controller.findAll);
router.get('/:id', controller.findById);
router.post('/', controller.create);
router.delete('/:id', controller.remove);

module.exports = router;