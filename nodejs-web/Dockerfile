FROM node:10-alpine

RUN mkdir /home/node/app

WORKDIR /home/node/app

COPY package*.json ./

RUN npm install

COPY . .

RUN ls

EXPOSE 8080

CMD ["node", "app.js"]
