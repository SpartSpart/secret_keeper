pipeline {
    agent any
    environment {
            AGENT = "dev_agent2"
        }
    stages {
        stage('Clean_Build') {
        agent {
           label AGENT
        }
           steps {
              	    sh 'gradle clean build'
           }
        }
        stage ('Docker_job'){
        agent {
           label AGENT
         }
           steps {
                    sh 'docker build -t password-keeper-api:1.0.0 .'
                    sh 'docker stop password-keeper-api || true && docker rm password-keeper-api || true'
                    sh 'docker run -d --net=host -p 58440:58440 --name password-keeper-api password-keeper-api:1.0.0'
           }
        }

    }
}