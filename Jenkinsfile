pipeline {
//     agent { docker { image 'maven:3.3.3' } }
    agent any

    tools {
        maven '3.8.1'

    }
    stages {
        stage('build') {
            steps {
                echo 'building the application...'
                sh 'mvn --version'
                sh 'mvn - clean install'
            }
        }

//         stage("test") {
//             steps {
//                 echo 'testing the application...'
//
//             }
//         }
//
//         stage("deploy") {
//             steps {
//                 echo 'deploying the application...'
//
//             }
//         }
    }
}