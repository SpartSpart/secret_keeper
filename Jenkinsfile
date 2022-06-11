

// when { branch "/master/*" }
pipeline {


    agent none

    environment {

              BRANCH = "${GIT_BRANCH}"


        }

    stages {
        stage ('Setup'){
           steps{
                echo "branch0 = " + BRANCH
                    script {
                            echo "branch1 = " + "${GIT_BRANCH}"
                             if (env.GIT_BRANCH == "origin/master") {
                                 agentLabel = "dev_agent2"
                             } else {
                                 agentLabel = "qa_agent1"
                             }
                     }
                sh 'printenv'
                     echo "branch2 = " + agentLabel
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
        stage ('Docker_job'){
        agent {
           label agentLabel
         }
           steps {
                    sh 'docker build -t password-keeper-api:1.0.0 .'
                    sh 'docker stop password-keeper-api || true && docker rm password-keeper-api || true'
                    sh 'docker run -d --net=host -p 58440:58440 --name password-keeper-api password-keeper-api:1.0.0'
           }
        }

    }
}