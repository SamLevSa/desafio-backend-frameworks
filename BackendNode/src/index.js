const express = require('express');
const userRoutes = require('./routes/user.routes');
const errorMiddleware = require('./middlewares/error.middleware');

const app = express();
app.use(express.json());

app.use('/api/users', userRoutes);
app.use(errorMiddleware);

app.listen(3000, () => console.log('Server running on port 3000'));