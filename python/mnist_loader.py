import cPickle
import gzip

import numpy

class Loader():

    def load_data(self):
        f = gzip.open('../data/mnist.pkl.gz', 'rb')
        training_data, validation_data, test_data = cPickle.load(f)
        f.close()
        return (training_data, validation_data, test_data)

    def load_data_wrapper(self):
        trainingData, validationData, testData = self.load_data()
        trainingInputs = [numpy.reshape(x, (784, 1)) for x in trainingData[0]]
        trainingResults = [self.vectorized_result(y) for y in trainingData[1]]
        trainingData = zip(trainingInputs, trainingResults)
        validationInputs = [numpy.reshape(x, (784, 1)) for x in validationData[0]]
        validationData = zip(validationInputs, validationData[1])
        testInputs = [numpy.reshape(x,(784,1)) for x in testData[0]]
        testData = zip(testInputs, testData[1])
        print "Training data\n"
        for training in trainingData:
            print training
        return (trainingData, validationData, testData)

    def vectorized_result(self, j):
        e = numpy.zeros((10,1))
        e[j] = 1.0
        return e

if __name__ == '__main__':
    loader = Loader()
    loader.load_data_wrapper()