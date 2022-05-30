pipeline {
    agent any

    stages {
        stage('Clean_Build') {
            steps {
              	    sh 'gradle clean build-environment-bundle -Penv=dev'
            }
        }
        stage ('Docker job'){
            steps {
                    sh 'docker build -t password-keeper:1.0.0 .'
                    sh 'docker stop password-keeper-api || true && docker rm password-keeper-api || true'
                    sh 'docker run -d --net=host -p 58440:58440 --name password-keeper-api password-keeper:1.0.0'
            }
        }
    }
}

