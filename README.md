# project_artifactId
projeto VRaptor4 Web
##init project
Windows

	git clone git@github.com:fidelisfelipe/backand-vraptor-heroku.git project_artifactId
	cd project_artifactId
	heroku create project_artifactId
	git init
	heroku git:remote -a project_artifactId
	git remote add github local_of_repo_git_hub
	git remote -v
	git add .
	git commit -m"init project"
	git push heroku master
	heroku restart
	heroku open

browser show: <b>{"user":"VRaptor!"}</b>

##init bd
	go: https://postgres.heroku.com/databases
	select you bd
	get params of security and update hibernate.cfg.xml
	create bd: /usuarios/json