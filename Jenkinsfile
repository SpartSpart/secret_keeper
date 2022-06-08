pipeline {
    agent {
    label "qa_agent1"
    }

    stages {
        stage('Clean_Build') {
            steps {
              	    sh 'gradle clean build'
            }
        }
        stage ('Docker job'){
            steps {
                    sh 'docker build -t password-keeper-api:1.0.0 .'
                    sh 'docker stop password-keeper-api || true && docker rm password-keeper-api || true'
                    sh 'docker run -d --net=host -p 58440:58440 --name password-keeper-api password-keeper-api:1.0.0'
            }
        }
    }

    agent {
    label "dev_agent2"
    }

    stages {
        stage('Clean_Build') {
            steps {
              	    sh 'gradle clean build'
            }
        }
        stage ('Docker job'){
            steps {
                    sh 'docker build -t password-keeper-api:1.0.0 .'
                    sh 'docker stop password-keeper-api || true && docker rm password-keeper-api || true'
                    sh 'docker run -d --net=host -p 58440:58440 --name password-keeper-api password-keeper-api:1.0.0'
            }
        }
    }
}

