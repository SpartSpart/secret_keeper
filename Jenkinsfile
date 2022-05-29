pipeline {
    agent any

    stages {
        stage('Hello') {
            steps {
               // echo 'Hello World'
		//sh 'gradle clean build'
		sh 'sudo ./gradlew build '
		sh 'sudo ./gradlew run'
            }
        }
    }
}

