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
        stage ('Setup'){
        agent any
           steps{
             echo "SourceBrunch= " + GIT_BRANCH
             echo "db_name= " + DB_NAME
             echo "db_login= " + DB_LOGIN
             echo "db_password= " + DB_PASSWORD
             echo "db_password= " + API_PORT
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
//                    --build-arg HTTP_PROXY=http://10.20.30.2:1234
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
                        sh 'docker run -d --net=host -p 58440:58440
                        --env DB_LOGIN=${DB_LOGIN}
                        --env DB_PASSWORD=${DB_PASSWORD}
                        --env DB_NAME=${DB_NAME}
                        --env API_PORT=${API_PORT}
                        --name password-keeper-api password-keeper-api:1.0.$TAG'
                   }
        }
    }
}