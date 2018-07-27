# Assignment One
## Add a user
`curl http://localhost:8080/api/users -X "POST" -H "Content-Type: application/json" -d "{\"firstName\":\"justin\",\"lastName\":\"miller\",\"email\":\"justinjmiller@gmail.com\",\"telephone\":\"8015551212\"}"`
## Get a user
`curl http://localhost:8080/api/users/justinjmiller@gmail.com
