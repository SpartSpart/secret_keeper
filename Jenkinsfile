pipeline {
    agent any

    stages {
        stage('Build_run') {
            steps {
		        sh 'gradle clean build'
		        sh 'docker build -t password-keeper:1.0.0 .'
		        sh 'docker stop password-keeper-api || true && docker rm password-keeper-api || true'
                sh 'docker run -d -p 58440:58440 --name password-keeper-api password-keeper:1.0.0'
            }
        }
    }
}

