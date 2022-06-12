pipeline {
    agent any
    environment {
            CURRENT_GIT_BRANCH = "${GIT_BRANCH}"
            DB_NAME = "${DB_NAME}"
            DB_LOGIN = "${DB_LOGIN}"
            DB_PASSWORD = "${DB_PASSWORD}"
            API_PORT = "${API_PORT}"

            DOCKERHUB=credentials('dockerhub')
            TAG = "${BUILD_NUMBER}"
        }
    stages {
        stage ('Setup_branch'){
        agent any
           steps{
             script{
                if (CURRENT_GIT_BRANCH == "origin/master")
                   agentLabel = "dev_agent2"
                else
                   agentLabel = "qa_agent1"
             }

           }
        }
        stage('Clean_Build') {
        agent {
           label agentLabel
        }
           steps {
              	    sh 'gradle clean build'
           }
        }
        stage ('Docker_build'){
                agent {
                   label agentLabel
                 }
                   steps {
                        sh 'docker build -t password-keeper-api:1.0.$TAG .'
                        sh 'docker tag password-keeper-api:1.0.$TAG spartspart/password-keeper-api:1.0.$TAG';
                   }
        }
        stage ('Docker_push_dockerhub'){
                agent {
                   label agentLabel
                 }
                   steps {
                        sh 'echo $DOCKERHUB_PSW | docker login -u $DOCKERHUB_USR --password-stdin'
                        sh 'docker push spartspart/password-keeper-api:1.0.$TAG'
                   }
        }
        stage ('Docker_run'){
                agent {
                   label agentLabel
                 }
                   steps {
                        sh 'docker stop password-keeper-api || true && docker rm password-keeper-api || true'
                        sh 'docker run -d --net=host -p $API_PORT:$API_PORT\
                        --env DB_LOGIN=${DB_LOGIN}\
                        --env DB_PASSWORD=${DB_PASSWORD}\
                        --env DB_NAME=${DB_NAME}\
                        --env API_PORT=${API_PORT}\
                        --name password-keeper-api password-keeper-api:1.0.$TAG'
                   }
        }
        stage ('Remove old images'){
                agent {
                   label agentLabel
                 }
                   steps {
                        sh 'docker image prune -a --force --filter "until=1h")'
                        echo "Success"
                   }
        }
    }
}