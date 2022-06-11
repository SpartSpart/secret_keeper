pipeline {
    agent any
    environment {
            DEV_AGENT = "dev_agent2"
            QA_AGENT = "qa_agent1"
        }
    stages {
        stage('Clean_Build_QA') {
        agent {
           label QA_AGENT
        }
           steps {
              	    sh 'gradle clean build'
           }
        }
        stage ('Docker_job_QA'){
        agent {
           label QA_AGENT
         }
           steps {
                    sh 'docker build -t password-keeper-api:1.0.0 .'
                    sh 'docker stop password-keeper-api || true && docker rm password-keeper-api || true'
                    sh 'docker run -d --net=host -p 58440:58440 --name password-keeper-api password-keeper-api:1.0.0'
           }
        }
        stage('Clean_Build_DEV') {
                agent {
                   label DEV_AGENT
                }
                   steps {
                      	    sh 'gradle clean build'
                   }
                }
                stage ('Docker_job_DEV'){
                agent {
                   label DEV_AGENT
                 }
                   steps {
                            sh 'docker build -t password-keeper-api:1.0.0 .'
                            sh 'docker stop password-keeper-api || true && docker rm password-keeper-api || true'
                            sh 'docker run -d --net=host -p 58440:58440 --name password-keeper-api password-keeper-api:1.0.0'
                   }
                }

    }
}