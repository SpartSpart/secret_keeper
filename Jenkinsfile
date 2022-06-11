// def agentLabel
// if (branch == "/master") {
//     agentLabel = "dev_agent2"
// } else {
//     agentLabel = "qa_agent1"
// }

// when { branch "/master/*" }
pipeline {
    agent none
    stages {
        stage ('Test'){
            agent any
                steps{
                    echo "Pulling..." + env.BRANCH_NAME
                    echo "SourceBrach= " + GIT_BRANCH
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