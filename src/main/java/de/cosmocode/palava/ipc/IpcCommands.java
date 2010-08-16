package de.cosmocode.palava.ipc;

import java.util.Set;

import com.google.inject.internal.Sets;

import de.cosmocode.palava.ipc.IpcCommand.Throw;
import de.cosmocode.palava.ipc.IpcCommand.Throws;

/**
 * Static utility class for {@link IpcCommand}s.
 *
 * @since 1.7
 * @author Willi Schoenborn
 */
public final class IpcCommands {

    private IpcCommands() {
        
    }
    
    /**
     * Returns a set of all {@link Throwable}s the given command is allowed to throw according to
     * it's documentation of {@link Throw} and {@link Throws} annotations.
     * 
     * @since 1.7
     * @param command the command to be checked
     * @return a set of all throwables the command is allowed to throw
     */
    public static Set<Class<? extends Throwable>> getThrows(IpcCommand command) {
        return getThrows(command.getClass());
    }

    /**
     * Returns a set of all {@link Throwable}s the given command is allowed to throw according to
     * it's documentation of {@link Throw} and {@link Throws} annotations.
     * 
     * @since 1.7
     * @param command the command to be checked
     * @return a set of all throwables the command is allowed to throw
     */
    public static Set<Class<? extends Throwable>> getThrows(Class<? extends IpcCommand> command) {
        final Set<Class<? extends Throwable>> throwables = Sets.newHashSet();
        
        if (command.isAnnotationPresent(Throw.class)) {
            throwables.add(command.getAnnotation(Throw.class).name());
        }
        
        if (command.isAnnotationPresent(Throws.class)) {
            for (Throw t : command.getAnnotation(Throws.class).value()) {
                throwables.add(t.name());
            }
        }
        
        return throwables;
    }
    
    /**
     * Checks whether the given command may throw the specified throwable.
     * 
     * @since 1.7
     * @param command the command to be checked
     * @param throwable the throwable to check for
     * @return true if the given command is allowed to throw the given throwable
     */
    public static boolean mayThrow(Class<? extends IpcCommand> command, Throwable throwable) {
        return mayThrow(command, throwable.getClass());
    }

    /**
     * Checks whether the given command may throw the specified throwable.
     * 
     * @since 1.7
     * @param command the command to be checked
     * @param throwable the throwable to check for
     * @return true if the given command is allowed to throw the given throwable
     */
    public static boolean mayThrow(Class<? extends IpcCommand> command, Class<? extends Throwable> throwable) {
        if (command.isAnnotationPresent(Throw.class)) {
            if (command.getAnnotation(Throw.class).name().equals(throwable)) {
                return true;
            }
        }
        
        if (command.isAnnotationPresent(Throws.class)) {
            for (Throw t : command.getAnnotation(Throws.class).value()) {
                if (t.name().equals(throwable)) {
                    return true;
                }
            }
        }
        
        return false;
    }

}
