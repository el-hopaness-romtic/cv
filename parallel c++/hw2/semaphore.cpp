#include <mutex>
#include <condition_variable>

class semaphore
{
private:
    std::mutex mutex_;
    std::condition_variable cv_;
    int count_;

public:
    semaphore(int count = 0) : count_(count) {}

    inline void release()
    {
        std::unique_lock<std::mutex> lock(mutex_);
        ++count_;
        lock.unlock();
        cv_.notify_one();
    }

    inline void acquire()
    {
        std::unique_lock<std::mutex> lock(mutex_);
        cv_.wait(lock, [&]{ return count_ > 0; });
        --count_;
    }
};