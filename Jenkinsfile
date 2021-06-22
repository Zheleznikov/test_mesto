pipeline {
    agent {
        docker {
            image "maven 3.6.0-jdk11"
        }

    }
//     agent any


    stages {
        stage('build') {
            steps {
                echo 'building the application...'
                sh 'mvn --version'
                sh 'mvn clean install'
            }
        }

        stage("test") {
            steps {
                echo 'run tests...'
                sh 'mvn test'
            }
        }
//
//         stage("deploy") {
//             steps {
//                 echo 'deploying the application...'
//
//             }
//         }
    }
}