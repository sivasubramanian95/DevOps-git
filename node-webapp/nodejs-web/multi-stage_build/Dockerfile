FROM node:10-alpine As first_stage
RUN mkdir /home/node/app
WORKDIR /home/node/app
RUN npm install


FROM first_stage
COPY package*.json ./
RUN npm install
COPY . .
EXPOSE 8080
ENTRYPOINT ["node"]
CMD ["app.js"]
