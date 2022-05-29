pipeline {
    agent any

    stages {
        stage('Hello') {
            steps {
               // echo 'Hello World'
		//sh 'gradle clean build'
		sh './gradlew build '
		sh './gradlew run'
            }
        }
    }
}

