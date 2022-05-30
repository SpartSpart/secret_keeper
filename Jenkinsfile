pipeline {
    agent any

    stages {
        stage('Clean_Build') {
            steps {
              	    sh 'gradle clean build'
            }
        }
        stage ('Docker job'){
            steps {
                    sh 'docker build -t password-keeper:1.0.0 .'
                    sh 'docker stop password-keeper-api || true && docker rm password-keeper-api || true'
                    sh 'docker run -d --net=host --name password-keeper-api password-keeper:1.0.0'
            }
        }
    }
}

