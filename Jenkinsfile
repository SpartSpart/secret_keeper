pipeline {
    agent none

    stages {
        stage('Clean_Build_QA') {
        agent {
           label "qa_agent1"
        }
           steps {
              	    sh 'gradle clean build'
           }
        }
        stage ('Docker_job_QA'){
        agent {
           label "qa_agent1"
         }
           steps {
                    sh 'docker build -t password-keeper-api:1.0.0 .'
                    sh 'docker stop password-keeper-api || true && docker rm password-keeper-api || true'
                    sh 'docker run -d --net=host -p 58440:58440 --name password-keeper-api password-keeper-api:1.0.0'
           }
        }

        stage('Clean_Build_DEV') {
                agent {
                   label "dev_agent2"
                }
                    steps {
                      	    sh 'gradle clean build'
                    }
                }
                stage ('Docker_job_DEV'){
                 agent {
                    label "dev_agent2"
                 }
                    steps {
                            sh 'docker build -t password-keeper-api:1.0.0 .'
                            sh 'docker stop password-keeper-api || true && docker rm password-keeper-api || true'
                            sh 'docker run -d --net=host -p 58440:58440 --name password-keeper-api password-keeper-api:1.0.0'
                    }
                }
    }
}