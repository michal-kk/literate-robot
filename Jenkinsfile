def subDirs

node {
    stage('checkout') {
        git 'https://github.com/michal-kk/literate-robot.git'
    }

    // what is this @tmp/ directory??
    subDirs = sh(returnStdout: true,
            script: 'ls -d */ | grep -ve "@tmp/$"').split('\n')
    
    stash excludes: '**/target/**', name: 'everything'
}

stage('parallel build') {
    def steps = [:]

    for (subDir in subDirs) {
        def localSubDir = subDir
        steps[localSubDir] = {
            buildInSubDir(localSubDir)
        }
    }

    parallel steps
}

def buildInSubDir(subDir) {
    node {
        sh 'rm -rf *'
        unstash 'everything'
        dir(subDir) {
            sh 'mvn clean test'
        }
    }
}

