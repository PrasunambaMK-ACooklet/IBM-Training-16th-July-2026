package com.bank.memory;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * TOPIC: Garbage Collection & Types Of Objects
 *
 * Java automatically reclaims memory used by objects that are no longer
 * reachable. This demo shows how to nudge/observe that process, and the
 * four reference "strengths" the JVM recognises, from strongest to weakest:
 *
 *   1. STRONG   - a normal reference; the object is NEVER collected while
 *                 a strong reference to it exists.
 *   2. SOFT     - collected only when the JVM is short on memory (good for
 *                 memory-sensitive caches, e.g. a cache of recent statements).
 *   3. WEAK     - collected at the NEXT garbage collection cycle if no
 *                 strong reference exists (good for metadata that shouldn't
 *                 keep an object alive, e.g. a listener registry).
 *   4. PHANTOM  - cannot be used to retrieve the object at all; only tells
 *                 you AFTER the object has been finalized/reclaimed, via a
 *                 ReferenceQueue - used for precise cleanup scheduling.
 */
public class GarbageCollectionDemo {

    static class TransactionReceipt {
        final String id;
        TransactionReceipt(String id) { this.id = id; }

        @Override
        protected void finalize() {
            // Deprecated in modern Java, shown only for educational purposes.
            System.out.println("Receipt " + id + " is being garbage collected.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // STRONG reference - stays alive as long as "strongReceipt" is in scope.
        TransactionReceipt strongReceipt = new TransactionReceipt("R-STRONG-1");

        // SOFT reference - object can be reclaimed if the JVM needs memory.
        SoftReference<TransactionReceipt> softReceipt =
                new SoftReference<>(new TransactionReceipt("R-SOFT-1"));

        // WEAK reference - eligible for collection at the very next GC cycle
        // once no strong reference points to it.
        WeakReference<TransactionReceipt> weakReceipt =
                new WeakReference<>(new TransactionReceipt("R-WEAK-1"));

        // PHANTOM reference - used with a ReferenceQueue to know exactly
        // when the object has actually been reclaimed, for cleanup tasks.
        ReferenceQueue<TransactionReceipt> queue = new ReferenceQueue<>();
        PhantomReference<TransactionReceipt> phantomReceipt =
                new PhantomReference<>(new TransactionReceipt("R-PHANTOM-1"), queue);

        System.out.println("Strong reference object id: " + strongReceipt.id);
        System.out.println("Soft reference still holds object? " + (softReceipt.get() != null));
        System.out.println("Weak reference still holds object? " + (weakReceipt.get() != null));

        // Explicitly dropping the only strong references to demonstrate eligibility for GC.
        // (System.gc() is only a HINT to the JVM, never a guarantee.)
        System.gc();
        Thread.sleep(200);

        System.out.println("After System.gc() hint, weak reference holds object? " + (weakReceipt.get() != null));
        System.out.println("PhantomReference.get() always returns: " + phantomReceipt.get() + " (by design)");
    }
}
